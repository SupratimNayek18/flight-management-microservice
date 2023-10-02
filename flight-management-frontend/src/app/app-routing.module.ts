import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { SigninComponent } from './signin/signin.component';
import { SearchFlightsComponent } from './search-flights/search-flights.component';
import { BookFlightComponent } from './book-flight/book-flight.component';
import { ShowBookingsComponent } from './show-bookings/show-bookings.component';
import { CheckInComponent } from './check-in/check-in.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { ViewAllFlightsComponent } from './admin/view-all-flights/view-all-flights.component';
import { AddFlightComponent } from './admin/add-flight/add-flight.component';
import { DeleteFlightComponent } from './admin/delete-flight/delete-flight.component';
import { ViewUserComponent } from './admin/view-user/view-user.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'signin', component: SigninComponent },
  { path: 'flights', component: SearchFlightsComponent },
  { path: 'bookFlight', component: BookFlightComponent },
  { path: 'showBookings', component: ShowBookingsComponent },
  { path: 'checkIn', component: CheckInComponent },
  { path: 'adminPanel', component: AdminPanelComponent },
  { path: 'adminPanel/viewAllFlights', component: ViewAllFlightsComponent },
  { path: 'adminPanel/addFlight', component: AddFlightComponent },
  { path: 'adminPanel/deleteFlight', component: DeleteFlightComponent },
  { path: 'adminPanel/viewUser', component: ViewUserComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
