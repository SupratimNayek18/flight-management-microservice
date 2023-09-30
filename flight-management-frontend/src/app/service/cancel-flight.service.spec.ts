import { TestBed } from '@angular/core/testing';

import { CancelFlightService } from './cancel-flight.service';

describe('CancelFlightService', () => {
  let service: CancelFlightService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CancelFlightService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
