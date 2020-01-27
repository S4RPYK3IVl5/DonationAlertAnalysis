import { TestBed } from '@angular/core/testing';

import { DaUserService } from './da-user.service';

describe('DaUserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DaUserService = TestBed.get(DaUserService);
    expect(service).toBeTruthy();
  });
});
