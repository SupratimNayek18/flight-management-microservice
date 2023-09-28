import { TestBed } from '@angular/core/testing';

import { BookFlightService } from './book-flight.service';

describe('BookFlightService', () => {
  let service: BookFlightService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookFlightService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
