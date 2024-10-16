import { Component } from '@angular/core';
import { provideIcons } from '@ng-icons/core';
import {
  lucideListMusic,
  lucidePlusCircle,
  lucidePodcast,
} from '@ng-icons/lucide';

import { positions } from '@/assets/constants/positions';
import { SideMenuComponent } from '@/components/side-menu/side-menu/side-menu.component';
import { CommonModule, NgOptimizedImage } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterModule } from '@angular/router';
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

@Component({
  selector: 'app-position-page',
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
    HlmScrollAreaComponent,

    BrnMenuTriggerDirective,
    BrnContextMenuTriggerDirective,
    HlmMenuComponent,
    HlmMenuGroupComponent,
    HlmMenuItemDirective,
    HlmSubMenuComponent,
    HlmMenuItemSubIndicatorComponent,
    HlmMenuSeparatorComponent,

    HlmCardDirective,
    NgOptimizedImage,
    CommonModule,
    HlmInputDirective,

    FormsModule,
    RouterLink,
    RouterModule,
  ],
  providers: [
    provideIcons({ lucidePlusCircle, lucideListMusic, lucidePodcast }),
  ],
  templateUrl: './position-page.component.html',
})
export class PositionPageComponent {
  searchText = '';

  positions = [...positions];

  searchKey(data: string): void {
    this.searchText = data;
    this.positions =
      data === ''
        ? [...positions]
        : positions.filter((nation) =>
            nation.name.toLowerCase().includes(data.toLowerCase())
          );
  }
}
