import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Resource1Component } from './resource1.component';

describe('Resource1Component', () => {
  let component: Resource1Component;
  let fixture: ComponentFixture<Resource1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Resource1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Resource1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
