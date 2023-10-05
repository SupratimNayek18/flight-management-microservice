import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { ApiService } from '../service/api.service';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  registrationForm:FormGroup;
  
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
  ) {
    this.registrationForm = this.formBuilder.group({
      firstName: ['',[Validators.required]],
    lastName:  ['',[Validators.required]],
    address:  ['',[Validators.required]],
    phoneNumber:  ['',[Validators.required,this.phoneNumberValidator()]],
    userName:  ['',[Validators.required]],
    password:  ['',[Validators.required,this.passwordValidator()]],
    })
  }

  phoneNumberValidator():ValidatorFn{
    return (control:AbstractControl):{[key:string]:any}|null =>{
      const phoneNumber = control.value;
      if(phoneNumber===null){
        return {'required':true};
      }
      if(!/^\d{10}$/.test(phoneNumber)){
        return {'invalidPhoneNumber':true};
      }
      return null;
    }
  }

  passwordValidator():ValidatorFn{
    return (control:AbstractControl):{[key:string]:any}|null =>{
      const password = control.value;
      if(password===null){
        return {'required':true};
      }
      if(!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$!%^&*()_+])[A-Za-z\d@#$!%^&*()_+]{8,}$/.test(password)){
        return {'invalidPassword':true};
      }
      return null;
    }
  }

  get phoneNumberControl(){
    return this.registrationForm.get('phoneNumber');
  }

  get passwordControl(){
    return this.registrationForm.get('password');
  }

  navigateToAbout() {
    this.router.navigate(['/register']);
  }

  handleRegistration() {
    if (this.registrationForm.valid) {
      // Create the formData object from the form values
      this.formData.firstName = this.registrationForm.get('firstName')?.value;
      this.formData.lastName = this.registrationForm.get('lastName')?.value;
      this.formData.address = this.registrationForm.get('address')?.value;
      this.formData.phoneNumber = this.registrationForm.get('phoneNumber')?.value;
      this.formData.userName = this.registrationForm.get('userName')?.value;
      this.formData.password = this.registrationForm.get('password')?.value;
  
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
}
