import { Component } from '@angular/core';
import { ViewUserService } from '../service/admin/view-user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent {
  user: any;
  constructor(private viewUserService: ViewUserService) {
    let data = sessionStorage.getItem('user');
    this.user = data ? JSON.parse(data) : null;
  }
}
