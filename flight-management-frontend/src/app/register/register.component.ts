import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  constructor(private router: Router, private apiService: ApiService) {}

  navigateToAbout() {
    this.router.navigate(['/register']);
  }

  handleClick(
    firstName: String,
    lastName: String,
    address: String,
    phoneNumber: String,
    userName: String,
    password: String
  ) {
    let userData = {
      userName: userName,
      password: password,
      firstName: firstName,
      lastName: lastName,
      address: address,
      phoneNumber: Number(phoneNumber),
    };

    this.apiService.registerUser(userData).subscribe(
      (response: any) => {
        console.log(response);
        this.router.navigate(['']);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }
}
