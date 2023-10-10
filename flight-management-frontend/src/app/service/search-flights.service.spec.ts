import { TestBed } from '@angular/core/testing';

import { SearchFlightsService } from './search-flights.service';

describe('SearchFlightsService', () => {
  let service: SearchFlightsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchFlightsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
