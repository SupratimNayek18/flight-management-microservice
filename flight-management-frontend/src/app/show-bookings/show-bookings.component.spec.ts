import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowBookingsComponent } from './show-bookings.component';

describe('ShowBookingsComponent', () => {
  let component: ShowBookingsComponent;
  let fixture: ComponentFixture<ShowBookingsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowBookingsComponent]
    });
    fixture = TestBed.createComponent(ShowBookingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
