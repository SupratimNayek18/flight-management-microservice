import { TestBed } from '@angular/core/testing';

import { DeleteFlightService } from './delete-flight.service';

describe('DeleteFlightService', () => {
  let service: DeleteFlightService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeleteFlightService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
