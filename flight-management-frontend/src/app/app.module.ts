import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { SigninComponent } from './signin/signin.component';
import { SearchFlightsComponent } from './search-flights/search-flights.component';
import { NoticesPromotionsComponent } from './notices-promotions/notices-promotions.component';
import { BookFlightCardContainerComponent } from './book-flight-card-container/book-flight-card-container.component';
import { BookFlightComponent } from './book-flight/book-flight.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ShowBookingsComponent } from './show-bookings/show-bookings.component';
import { CheckInComponent } from './check-in/check-in.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { ViewAllFlightsComponent } from './admin/view-all-flights/view-all-flights.component';
import { AddFlightComponent } from './admin/add-flight/add-flight.component';
import { DeleteFlightComponent } from './admin/delete-flight/delete-flight.component';
import { ViewUserComponent } from './admin/view-user/view-user.component';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    HomeComponent,
    SigninComponent,
    SearchFlightsComponent,
    NoticesPromotionsComponent,
    BookFlightCardContainerComponent,
    BookFlightComponent,
    ShowBookingsComponent,
    CheckInComponent,
    AdminPanelComponent,
    ViewAllFlightsComponent,
    AddFlightComponent,
    DeleteFlightComponent,
    ViewUserComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
