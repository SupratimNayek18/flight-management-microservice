import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ViewUserService } from 'src/app/service/admin/view-user.service';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css'],
})
export class ViewUserComponent {
  user: any;
  userName: string = '';
  constructor(
    private viewUserService: ViewUserService,
    private http: HttpClient
  ) {}

  viewUser() {
    if (this.userName != null) {
      this.viewUserService.viewUser(this.userName).subscribe(
        (response) => {
          this.user = response;
          console.log(this.user);
        },
        (error) => {
          alert('Something went wrong');
        }
      );
    }
  }

  deleteUser() {
    if (this.userName != null) {
      this.http
        .delete(`http://localhost:9090/user/deleteUser/${this.userName}`, {
          responseType: 'text',
        })
        .subscribe((response) => {
          alert(response);
        });
    }
  }
}
