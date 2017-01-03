/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { FixturesService } from './../fixtures.service.ts';

describe('FixturesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FixturesService]
    });
  });

  it('should ...', inject([FixturesService], (service: FixturesService) => {
    expect(service).toBeTruthy();
  }));
});
