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
import { FormsModule } from '@angular/forms';

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
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
