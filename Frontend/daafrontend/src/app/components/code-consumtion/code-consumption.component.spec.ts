import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CodeConsumptionComponent } from './code-consumption.component';

describe('CodeConsumtionComponent', () => {
  let component: CodeConsumptionComponent;
  let fixture: ComponentFixture<CodeConsumptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CodeConsumptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CodeConsumptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
