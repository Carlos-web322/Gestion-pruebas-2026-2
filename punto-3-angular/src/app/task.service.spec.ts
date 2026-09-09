import { TestBed } from '@angular/core/testing';

import { TaskService } from './task.service';

describe('TaskService', () => {
  let service: TaskService;

  beforeEach(() => {
    localStorage.clear();
    TestBed.configureTestingModule({});
    service = TestBed.inject(TaskService);
  });

  it('se crea correctamente', () => {
    expect(service).toBeTruthy();
  });

  it('inicia sin tareas', () => {
    expect(service.getAll()).toEqual([]);
  });

  it('crea una tarea y le asigna un id', () => {
    const task = service.create('Comprar pan');

    expect(task.id).toBeTruthy();
    expect(task.title).toBe('Comprar pan');
    expect(task.completed).toBe(false);
    expect(service.getAll()).toEqual([task]);
  });

  it('persiste las tareas creadas en localStorage', () => {
    const task = service.create('Comprar pan');

    const raw = localStorage.getItem('tasks');
    expect(JSON.parse(raw!)).toEqual([task]);
  });

  it('lee las tareas existentes de localStorage al construirse', () => {
    const seeded = [{ id: '1', title: 'Ya guardada', completed: false }];
    localStorage.setItem('tasks', JSON.stringify(seeded));

    const freshService = new TaskService();

    expect(freshService.getAll()).toEqual(seeded);
  });

  it('actualiza una tarea por id', () => {
    const task = service.create('Comprar pan');

    const updated = service.update(task.id, { title: 'Comprar leche', completed: true });

    expect(updated).toEqual({ id: task.id, title: 'Comprar leche', completed: true });
    expect(service.getAll()).toEqual([updated!]);
  });

  it('devuelve null al actualizar una tarea que no existe', () => {
    const updated = service.update('missing-id', { title: 'x' });

    expect(updated).toBeNull();
  });

  it('elimina una tarea por id', () => {
    const task = service.create('Comprar pan');

    service.delete(task.id);

    expect(service.getAll()).toEqual([]);
  });

  it('no toca otras tareas al eliminar una', () => {
    const a = service.create('A');
    const b = service.create('B');

    service.delete(a.id);

    expect(service.getAll()).toEqual([b]);
  });
});
