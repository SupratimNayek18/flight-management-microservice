import { TestBed } from '@angular/core/testing';

import { CheckInServiceService } from './check-in-service.service';

describe('CheckInServiceService', () => {
  let service: CheckInServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckInServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
