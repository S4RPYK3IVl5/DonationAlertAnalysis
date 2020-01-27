import { TestBed } from '@angular/core/testing';

import { DaTokenService } from './da-token.service';

describe('DaTokenService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DaTokenService = TestBed.get(DaTokenService);
    expect(service).toBeTruthy();
  });
});
