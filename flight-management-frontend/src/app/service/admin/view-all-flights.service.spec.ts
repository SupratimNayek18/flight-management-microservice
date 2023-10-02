import { TestBed } from '@angular/core/testing';

import { ViewAllFlightsService } from './view-all-flights.service';

describe('ViewAllFlightsService', () => {
  let service: ViewAllFlightsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewAllFlightsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
