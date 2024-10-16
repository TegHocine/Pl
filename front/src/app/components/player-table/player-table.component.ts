import { Player } from '@/types/player.type';
import { Component, Input } from '@angular/core';
import {
  HlmCaptionComponent,
  HlmTableComponent,
  HlmTdComponent,
  HlmThComponent,
  HlmTrowComponent,
} from '@spartan-ng/ui-table-helm';

@Component({
  selector: 'app-player-table',
  standalone: true,
  imports: [
    HlmTableComponent,
    HlmTrowComponent,
    HlmThComponent,
    HlmTdComponent,
    HlmCaptionComponent,
  ],
  providers: [],
  host: {
    class: 'w-full overflow-x-auto',
  },
  templateUrl: './player-table.component.html',
})
export class PlayerTableComponent {
  @Input() players: Player[] = [];
}
