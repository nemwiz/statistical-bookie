import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AggregationSectionComponent } from './aggregation-section.component';

describe('AggregationSectionComponent', () => {
  let component: AggregationSectionComponent;
  let fixture: ComponentFixture<AggregationSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AggregationSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AggregationSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
