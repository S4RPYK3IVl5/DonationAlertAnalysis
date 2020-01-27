import { TestBed } from '@angular/core/testing';

import { DaAPIService } from './da-api.service';

describe('DaAPIService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DaAPIService = TestBed.get(DaAPIService);
    expect(service).toBeTruthy();
  });
});
