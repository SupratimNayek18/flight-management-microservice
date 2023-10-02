import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent {
  formData = { userName: '', password: '' };

  constructor(private router: Router, private apiService: ApiService) {
    if (sessionStorage.getItem('token') != null) {
      this.router.navigate(['']);
    }
  }

  handleSignin(formData: any) {
    this.apiService.signin(this.formData).subscribe(
      (response: any) => {
        sessionStorage.setItem('userName', response.userName);
        sessionStorage.setItem('token', response.token);
        sessionStorage.setItem('role', response.role);
        this.router.navigate(['']);
      },
      (error: any) => {
        alert('Invalid username or password. Please Try Again');
      }
    );
  }
}
