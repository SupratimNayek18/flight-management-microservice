import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { SigninComponent } from './signin/signin.component';
import { SearchFlightsComponent } from './search-flights/search-flights.component';
import { BookFlightComponent } from './book-flight/book-flight.component';
import { ShowBookingsComponent } from './show-bookings/show-bookings.component';
import { CheckInComponent } from './check-in/check-in.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'signin', component: SigninComponent },
  { path: 'flights', component: SearchFlightsComponent },
  { path: 'bookFlight', component: BookFlightComponent },
  { path: 'showBookings', component: ShowBookingsComponent },
  { path: 'checkIn', component: CheckInComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
