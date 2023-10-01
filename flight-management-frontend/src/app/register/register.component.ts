import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { ApiService } from '../service/api.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  formData = {
    firstName: '',
    lastName: '',
    address: '',
    phoneNumber: '',
    userName: '',
    password: '',
  };

  constructor(
    private router: Router,
    private apiService: ApiService,
    private formBuilder: FormBuilder
  ) {}

  navigateToAbout() {
    this.router.navigate(['/register']);
  }

  handleRegistration(formData: any) {
    this.apiService.registerUser(this.formData).subscribe(
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
