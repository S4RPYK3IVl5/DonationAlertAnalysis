import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DaUserComponent} from './daUser.component';

describe('DaUserComponent', () => {
  let component: DaUserComponent;
  let fixture: ComponentFixture<DaUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DaUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DaUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
