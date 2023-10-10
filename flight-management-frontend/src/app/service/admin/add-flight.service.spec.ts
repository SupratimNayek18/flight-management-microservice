import { TestBed } from '@angular/core/testing';

import { AddFlightService } from './add-flight.service';

describe('AddFlightService', () => {
  let service: AddFlightService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddFlightService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
