import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteFlightComponent } from './delete-flight.component';

describe('DeleteFlightComponent', () => {
  let component: DeleteFlightComponent;
  let fixture: ComponentFixture<DeleteFlightComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeleteFlightComponent]
    });
    fixture = TestBed.createComponent(DeleteFlightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
