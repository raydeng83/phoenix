import { TestBed, inject } from '@angular/core/testing';

import { ResourceGuardService } from './resource-guard.service';

describe('ResourceGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ResourceGuardService]
    });
  });

  it('should be created', inject([ResourceGuardService], (service: ResourceGuardService) => {
    expect(service).toBeTruthy();
  }));
});
