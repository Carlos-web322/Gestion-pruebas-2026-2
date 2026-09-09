import { TestBed, fakeAsync, tick } from '@angular/core/testing';
import { App } from './app';

describe('App', () => {
  beforeEach(async () => {
    localStorage.clear();
    await TestBed.configureTestingModule({
      imports: [App],
    }).compileComponents();
  });

  function setup() {
    const fixture = TestBed.createComponent(App);
    fixture.detectChanges();
    tick(); // flush NgForm's deferred control registration (resolvedPromise.then)
    fixture.detectChanges();
    const el = fixture.nativeElement as HTMLElement;
    return { fixture, el };
  }

  function typeInto(input: HTMLInputElement, value: string) {
    input.value = value;
    input.dispatchEvent(new Event('input', { bubbles: true }));
  }

  it('se crea correctamente', fakeAsync(() => {
    const { fixture } = setup();
    expect(fixture.componentInstance).toBeTruthy();
  }));

  it('muestra un estado vacío sin tareas', fakeAsync(() => {
    const { el } = setup();
    expect(el.querySelector('li')).toBeNull();
    expect(el.textContent).toContain('Sin tareas');
  }));

  it('agrega una tarea a través del formulario', fakeAsync(() => {
    const { fixture, el } = setup();
    typeInto(el.querySelector<HTMLInputElement>('input[name="title"]')!, 'Comprar pan');
    fixture.detectChanges();
    el.querySelector<HTMLButtonElement>('form button[type="submit"]')!.click();
    fixture.detectChanges();

    const items = el.querySelectorAll('li');
    expect(items.length).toBe(1);
    expect(items[0].textContent).toContain('Comprar pan');
  }));

  it('limpia el input después de agregar una tarea', fakeAsync(() => {
    const { fixture, el } = setup();
    const input = el.querySelector<HTMLInputElement>('input[name="title"]')!;
    typeInto(input, 'Comprar pan');
    fixture.detectChanges();
    el.querySelector<HTMLButtonElement>('form button[type="submit"]')!.click();
    tick();
    fixture.detectChanges();

    expect(input.value).toBe('');
  }));

  it('marca una tarea como completada', fakeAsync(() => {
    const { fixture, el } = setup();
    typeInto(el.querySelector<HTMLInputElement>('input[name="title"]')!, 'Comprar pan');
    fixture.detectChanges();
    el.querySelector<HTMLButtonElement>('form button[type="submit"]')!.click();
    fixture.detectChanges();

    el.querySelector<HTMLInputElement>('li input[type="checkbox"]')!.click();
    fixture.detectChanges();

    expect(el.querySelector('li')?.classList.contains('completed')).toBe(true);
  }));

  it('edita el título de una tarea', fakeAsync(() => {
    const { fixture, el } = setup();
    typeInto(el.querySelector<HTMLInputElement>('input[name="title"]')!, 'Comprar pan');
    fixture.detectChanges();
    el.querySelector<HTMLButtonElement>('form button[type="submit"]')!.click();
    fixture.detectChanges();

    el.querySelector<HTMLButtonElement>('li button.edit')!.click();
    fixture.detectChanges();
    tick();
    fixture.detectChanges();

    typeInto(el.querySelector<HTMLInputElement>('li input[name="editTitle"]')!, 'Comprar leche');
    fixture.detectChanges();
    el.querySelector<HTMLButtonElement>('li form.edit-form button[type="submit"]')!.click();
    fixture.detectChanges();

    expect(el.querySelector('li')?.textContent).toContain('Comprar leche');
  }));

  it('elimina una tarea', fakeAsync(() => {
    const { fixture, el } = setup();
    typeInto(el.querySelector<HTMLInputElement>('input[name="title"]')!, 'Comprar pan');
    fixture.detectChanges();
    el.querySelector<HTMLButtonElement>('form button[type="submit"]')!.click();
    fixture.detectChanges();

    el.querySelector<HTMLButtonElement>('li button.delete')!.click();
    fixture.detectChanges();

    expect(el.querySelectorAll('li').length).toBe(0);
  }));
});
