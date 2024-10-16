import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterModule } from '@angular/router';
import { provideIcons } from '@ng-icons/core';
import {
  lucideGlobe,
  lucideMapPin,
  lucideMenu,
  lucideUsers,
} from '@ng-icons/lucide';
import { HlmIconComponent } from '@spartan-ng/ui-icon-helm';
import { HlmScrollAreaComponent } from '@spartan-ng/ui-scrollarea-helm';
import {
  BrnSheetContentDirective,
  BrnSheetTriggerDirective,
} from '@spartan-ng/ui-sheet-brain';
import {
  HlmSheetComponent,
  HlmSheetContentComponent,
  HlmSheetDescriptionDirective,
  HlmSheetFooterComponent,
  HlmSheetHeaderComponent,
  HlmSheetTitleDirective,
} from '@spartan-ng/ui-sheet-helm';
import { SideMenuButtonComponent } from '../side-menu-button/side-menu-button.component';

interface ListItem {
  text: string;
  icon: string;
  link: string;
}

@Component({
  selector: 'app-side-menu',
  standalone: true,
  host: {
    class: 'block',
  },
  imports: [
    SideMenuButtonComponent,
    HlmIconComponent,
    HlmScrollAreaComponent,
    NgClass,
    HlmSheetComponent,
    HlmSheetContentComponent,
    HlmSheetDescriptionDirective,
    HlmSheetFooterComponent,
    HlmSheetHeaderComponent,
    HlmSheetTitleDirective,
    BrnSheetContentDirective,
    BrnSheetTriggerDirective,
    RouterLink,
    RouterModule,
  ],
  providers: [
    provideIcons({
      lucideMenu,
      lucideGlobe,
      lucideUsers,
      lucideMapPin,
    }),
  ],
  templateUrl: './side-menu.component.html',
})
export class SideMenuComponent {
  navList: ListItem[] = [
    { text: 'Nation', icon: 'lucideGlobe', link: '/' },
    { text: 'Team', icon: 'lucideUsers', link: '/team' },
    { text: 'Position', icon: 'lucideMapPin', link: '/position' },
  ];
}
