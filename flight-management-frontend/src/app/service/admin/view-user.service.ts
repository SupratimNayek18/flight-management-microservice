import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ViewUserService {
  constructor(private http: HttpClient) {}

  viewUser(userName: string) {
    let getUserUrl = `http://localhost:9090/user/getUser/${userName}`;
    return this.http.get(getUserUrl);
  }
}
