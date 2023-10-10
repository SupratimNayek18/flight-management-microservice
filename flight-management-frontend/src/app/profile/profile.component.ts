import { Component } from '@angular/core';
import { ViewUserService } from '../service/admin/view-user.service';
import { ApiService } from '../service/api.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent {
  user: any;
  constructor(
    private viewUserService: ViewUserService,
    private http: HttpClient,
    private router: Router
  ) {
    let data = sessionStorage.getItem('user');
    this.user = data ? JSON.parse(data) : null;
  }

  deleteProfile() {
    let res = confirm('Are you sure you want to delete your profile?');
    if (res) {
      this.http
        .delete(`http://localhost:9090/user/deleteUser/${this.user.userName}`, {
          responseType: 'text',
        })
        .subscribe((response) => {
          alert(response);
          sessionStorage.clear();
          this.router.navigate(['']);
        });
    }
  }
}
