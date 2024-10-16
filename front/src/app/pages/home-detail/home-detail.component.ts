import { PaginationComponent } from '@/components/pagination/pagination.component';
import { PlayerTableComponent } from '@/components/player-table/player-table.component';
import { SideMenuComponent } from '@/components/side-menu/side-menu/side-menu.component';
import { PlayerService } from '@/service/player.service';
import { PaginationResponse } from '@/types/global.type';
import { Player } from '@/types/player.type';
import { CommonModule } from '@angular/common';
import { Component, effect, OnDestroy, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HlmButtonDirective } from '@spartan-ng/ui-button-helm';
import { HlmCardDirective } from '@spartan-ng/ui-card-helm';
import { HlmIconComponent } from '@spartan-ng/ui-icon-helm';
import { HlmInputDirective } from '@spartan-ng/ui-input-helm';
import {
  BrnContextMenuTriggerDirective,
  BrnMenuTriggerDirective,
} from '@spartan-ng/ui-menu-brain';
import {
  HlmMenuComponent,
  HlmMenuGroupComponent,
  HlmMenuItemDirective,
  HlmMenuItemSubIndicatorComponent,
  HlmMenuSeparatorComponent,
  HlmSubMenuComponent,
} from '@spartan-ng/ui-menu-helm';
import { HlmScrollAreaComponent } from '@spartan-ng/ui-scrollarea-helm';
import { HlmSeparatorDirective } from '@spartan-ng/ui-separator-helm';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-home-detail',
  standalone: true,
  host: {
    class: 'block',
  },
  imports: [
    HlmScrollAreaComponent,
    SideMenuComponent,
    HlmButtonDirective,
    HlmIconComponent,
    HlmSeparatorDirective,
    BrnMenuTriggerDirective,
    BrnContextMenuTriggerDirective,
    HlmMenuComponent,
    HlmMenuGroupComponent,
    HlmMenuItemDirective,
    HlmSubMenuComponent,
    HlmMenuItemSubIndicatorComponent,
    HlmMenuSeparatorComponent,
    HlmCardDirective,
    CommonModule,
    HlmInputDirective,
    FormsModule,
    PlayerTableComponent,
    PaginationComponent,
  ],
  providers: [PlayerService],
  templateUrl: './home-detail.component.html',
})
export class HomeDetailComponent implements OnDestroy {
  _players: PaginationResponse<Player> | null = null;
  private _unsubscribeAll: Subject<any> = new Subject<any>();

  nation = '';

  page = signal(1);
  pageSize = signal(10);
  totalProducts = signal(1);

  constructor(
    private playerService: PlayerService,
    private route: ActivatedRoute
  ) {
    this.nation = this.route.snapshot.paramMap.get('nation') || '';
    effect(() => {
      this.playerService
        .getPlayersByNation(this.nation, this.page(), this.pageSize())
        .subscribe((data) => {
          this._players = data;
          this.totalProducts.set(data.totalItems);
        });
    });
  }

  ngOnDestroy(): void {
    // Unsubscribe from all subscriptions
    this._unsubscribeAll.next(null);
    this._unsubscribeAll.complete();
  }
}
