import { TestBed } from '@angular/core/testing';

import { GetBookingService } from './get-booking.service';

describe('GetBookingService', () => {
  let service: GetBookingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetBookingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
