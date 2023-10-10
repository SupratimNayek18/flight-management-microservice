import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css'],
})
export class AdminPanelComponent {
  userName: string | null;
  constructor(private router: Router) {
    this.userName = sessionStorage.getItem('userName');
  }

  ngOnInit() {
    if (sessionStorage.getItem('role') !== 'ROLE_ADMIN') {
      this.router.navigate(['']);
    }
  }

  handleLogout() {
    sessionStorage.clear();
    this.router.navigate(['']);
  }
}
