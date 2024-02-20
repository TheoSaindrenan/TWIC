import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientInfosComponent } from './client-infos.component';

describe('ClientInfosComponent', () => {
  let component: ClientInfosComponent;
  let fixture: ComponentFixture<ClientInfosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientInfosComponent]
    });
    fixture = TestBed.createComponent(ClientInfosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
