import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(private router:Router){}

  navigateToAbout(){
    this.router.navigate(['/register']);
  }

  handleClick(firstName:String,lastName:String,address:String,email:String,password:String){
    alert(firstName+" "+lastName+ " " +address +" "+ address+" "+email+" "+password);
  }

}
