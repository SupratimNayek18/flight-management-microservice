import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private http: HttpClient) {}

  registerUser(data: any): any {
    const registerUrl = 'http://localhost:9090/user/register';

    return this.http.post<any>(registerUrl, data);
  }

  signin(data: any): any {
    const signinUrl = 'http://localhost:9090/user/signIn';

    return this.http.post<any>(signinUrl, data);
  }

  updateUser(data: any): any {
    const updateUrl = 'http://localhost:9090/user/updateUser';

    return this.http.put<any>(updateUrl, data);
  }
}
