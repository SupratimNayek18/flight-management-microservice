import { Component } from '@angular/core';
import { LogoutService } from '../service/logout.service';
import { Router, UrlSegment } from '@angular/router';
import { ViewUserService } from '../service/admin/view-user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  name: string = '';
  userName: string | null = null;

  constructor(
    private logoutService: LogoutService,
    private router: Router,
    private viewUserService: ViewUserService
  ) {}

  ngOnInit() {
    if (sessionStorage.getItem('role') === 'ROLE_ADMIN') {
      this.router.navigate(['adminPanel']);
    }
    this.userName = sessionStorage.getItem('userName');
    if (this.userName != null) {
      this.viewUserService
        .viewUser(this.userName)
        .subscribe((response: any) => {
          this.viewUserService.setUser(JSON.stringify(response));
          this.name = response.firstName + ' ' + response.lastName;
        });
    }
  }

  handleLogout() {
    this.logoutService.logout();
    window.location.reload();
  }
}
