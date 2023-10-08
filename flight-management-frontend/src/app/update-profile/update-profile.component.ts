import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { ApiService } from '../service/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css'],
})
export class UpdateProfileComponent {
  updateForm: FormGroup;
  status: string | null = null;

  formData = {
    userName: '',
    firstName: null,
    lastName: null,
    address: null,
    phoneNumber: null,
  };

  constructor(
    private apiService: ApiService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    if (sessionStorage.getItem('userName') == null) {
      this.router.navigate(['']);
    } else {
      let userName = sessionStorage.getItem('userName');
      this.formData.userName = userName ? userName : '';
    }
    this.updateForm = this.formBuilder.group({
      firstName: [null],
      lastName: [null],
      address: [null],
      phoneNumber: [null],
    });
  }

  phoneNumberValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const phoneNumber = control.value;
      if (phoneNumber === null) {
        return { required: true };
      }
      if (!/^\d{10}$/.test(phoneNumber)) {
        return { invalidPhoneNumber: true };
      }
      return null;
    };
  }

  get phoneNumberControl() {
    return this.updateForm.get('phoneNumber');
  }

  handleUpdate() {
    if (this.updateForm.valid) {
      // Create the formData object from the form values
      this.formData.firstName = this.updateForm.get('firstName')?.value;
      this.formData.lastName = this.updateForm.get('lastName')?.value;
      this.formData.address = this.updateForm.get('address')?.value;
      this.formData.phoneNumber = this.updateForm.get('phoneNumber')?.value;

      console.log(this.formData);

      this.apiService.updateUser(this.formData).subscribe(
        (response: any) => {
          console.log(response);
          this.status = 'Profile Updated Successfully';
        },
        (error: any) => {
          console.log(error);
        }
      );
    }
  }
}
