import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookFlightCardContainerComponent } from './book-flight-card-container.component';

describe('BookFlightCardContainerComponent', () => {
  let component: BookFlightCardContainerComponent;
  let fixture: ComponentFixture<BookFlightCardContainerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookFlightCardContainerComponent]
    });
    fixture = TestBed.createComponent(BookFlightCardContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
