# Kingsley User Guide

![Ui Screenshot](Ui.png)

Kingsley is a real-time virtual assistant that can help you with task management, tracking of deadlines and efficient scheduling. 

## Adding Todos

Add a simple task with a description


### Format
```
todo DESCRIPTION 
```

### Example
```
todo CS2100 Lab 
```

### Expected Output
```
Got it. I've added this task: 
    [T][] CS2100 Lab
Now you have 7 tasks in the list. 
```


## Adding Deadlines

Add a task that is due by a specified date and time


### Format
```
deadline DESCRIPTION /by DD/MM/YYYY HHmm
```

### Example
```
deadline PF1101 Project 2 /by 12/12/2024 1800
```

### Expected Output 
```
Got it. I've added this task: 
    [D][] PF1101 Project 2 (By Dec 12 2024, 6:00pm) 
Now you have 7 tasks in the list. 
```


## Adding Events

Add a task that has a start and end date/time. 

Kingsley will not allow addition of event if it overlaps 
with other events. It will display the clashing events if this occurs 


### Format
```
event DESCRIPTION /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm
```

### Example
```
event Project Meeting /from 30/09/2025 1000 /to 30/09/2025 1200
```

### Expected Output
```
Got it. I've added this task:                 
    [E][] Project Meeting (from: Sep 30 2025,
10:00am to Sep 30 2025, 12:00pm)              
Now you have 7 tasks in the list.        
```


## Listing Current Tasks 

Show all the tasks that has been added into task list.


### Format
```
list
```

### Expected Output
```
Here are the tasks in your list:
    1. [T][] Finish iP 
    2. [D][] Karaoke with the homies (by: Dec 22 2023, 7:00pm) 
```

## Deleting Tasks

Delete a task you do not want to keep anymore or wrongly added.

### Format
```
delete TASKNUMBER
```

### Example
```
delete 2 
```
### Expected Output
```
Noted, I've removed this task.
    [T][] CS2100 Lab 
Now you have 6 tasks in your list. 
```

## Marking Tasks

Mark a task as completed by its index.

### Format
```
mark TASKNUMBER
```

### Example
```
mark 2 
```
### Expected Output
```
Nice! I've marked this task as done: 
    [E][X] Project Meeting (from Sep 30 2025, 
10:00 am to Sep 30 2025, 12:00pm) 
```

## Unmarking Tasks

Mark a task as uncompleted by its index.

### Format
```
unmark INDEX
```

### Example
```
unmark 2 
```
### Expected Output
```
Nice! I've marked this task as not done yet: 
    [E][] Project Meeting (from Sep 30 2025, 
10:00 am to Sep 30 2025, 12:00pm) 
```
## Finding tasks
Show tasks whose description match the given keyword (case-sensitive)
### Format
```
find KEYWORD
```
### Example
```
find Project 
```
### Expected Output
```
Here are the matching tasks in your list: 
    1. [D][] PF1101 Project 2 (by Dec 12 2024, 6:00pm) 
    2. [E][] Project Meeting (from: Sep 30 2025, 10:00am to Sep 30 2025, 12:00pm) 
```

## Deleting tasks

Delete a task from the task list by its index
### Format
```
delete INDEX
```

### Example
```
delete 2 
```
### Expected Output
```
Noted. I've removed this task. 
    [T][] Finish iP
Now you have 6 tasks in the list. 
```

## Exiting from program

Say bye to Kingsley 
### Format
```
bye
```

### Expected Output
```
Bye! Hope to see you again soon!
```





