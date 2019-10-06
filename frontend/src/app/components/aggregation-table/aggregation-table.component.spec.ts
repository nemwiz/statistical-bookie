import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AggregationTableComponent } from './aggregation-table.component';

describe('AggregationTableComponent', () => {
  let component: AggregationTableComponent;
  let fixture: ComponentFixture<AggregationTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AggregationTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AggregationTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
