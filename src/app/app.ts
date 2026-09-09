import { Component, signal } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Task } from './task.model';
import { TaskService } from './task.service';

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly tasks = signal<Task[]>([]);
  protected newTitle = '';
  protected editingId = signal<string | null>(null);
  protected editTitle = '';

  constructor(private readonly taskService: TaskService) {
    this.tasks.set(this.taskService.getAll());
  }

  addTask(form: NgForm): void {
    const title = this.newTitle.trim();
    if (!title) return;

    this.taskService.create(title);
    this.tasks.set(this.taskService.getAll());
    form.resetForm();
  }

  toggleCompleted(task: Task): void {
    this.taskService.update(task.id, { completed: !task.completed });
    this.tasks.set(this.taskService.getAll());
  }

  deleteTask(task: Task): void {
    this.taskService.delete(task.id);
    this.tasks.set(this.taskService.getAll());
  }

  startEdit(task: Task): void {
    this.editingId.set(task.id);
    this.editTitle = task.title;
  }

  saveEdit(task: Task): void {
    const title = this.editTitle.trim();
    if (title) {
      this.taskService.update(task.id, { title });
      this.tasks.set(this.taskService.getAll());
    }
    this.editingId.set(null);
  }
}
