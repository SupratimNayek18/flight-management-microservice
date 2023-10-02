import { TestBed } from '@angular/core/testing';

import { ViewUserService } from './view-user.service';

describe('ViewUserService', () => {
  let service: ViewUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
