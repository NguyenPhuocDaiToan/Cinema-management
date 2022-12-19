import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountstatisticComponent } from './accountstatistic.component';

describe('AccountstatisticComponent', () => {
  let component: AccountstatisticComponent;
  let fixture: ComponentFixture<AccountstatisticComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountstatisticComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountstatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
