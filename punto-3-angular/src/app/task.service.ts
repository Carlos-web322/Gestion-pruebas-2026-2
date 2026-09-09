import { Injectable } from '@angular/core';
import { Task } from './task.model';

const STORAGE_KEY = 'tasks';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private tasks: Task[] = this.load();

  getAll(): Task[] {
    return this.tasks;
  }

  create(title: string): Task {
    const task: Task = { id: crypto.randomUUID(), title, completed: false };
    this.tasks = [...this.tasks, task];
    this.save();
    return task;
  }

  update(id: string, changes: Partial<Omit<Task, 'id'>>): Task | null {
    const index = this.tasks.findIndex((t) => t.id === id);
    if (index === -1) return null;

    const updated = { ...this.tasks[index], ...changes };
    this.tasks = [...this.tasks.slice(0, index), updated, ...this.tasks.slice(index + 1)];
    this.save();
    return updated;
  }

  delete(id: string): void {
    this.tasks = this.tasks.filter((t) => t.id !== id);
    this.save();
  }

  private load(): Task[] {
    const raw = localStorage.getItem(STORAGE_KEY);
    return raw ? JSON.parse(raw) : [];
  }

  private save(): void {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(this.tasks));
  }
}
