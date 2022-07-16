import React, {useState} from "react";
import { ListItem, ListItemText, InputBase, Checkbox, ListItemSecondaryAction, IconButton } from '@mui/material'
import { DeleteOutline } from "@mui/icons-material";

const Todo = (props) => {

  const [currentTodoTitle, setcurrentTodoTitle] = useState(props.item.title)

  const deleteTodoHandler = () => {
    props.onDelete(props.item)
  }

  const checkboxHandler = () => {
    props.onCheck(props.item)
  }

  const offReadOnlyMode = () => {
    props.toggleReadOnly(props.item, false)
  }

  const onReadOnlyMode = (event) => {
    if (event.key === 'Enter') {
      props.toggleReadOnly(props.item, true)
      const newTodo = { id: props.item.id, title: currentTodoTitle, done: props.item.done, readOnly: props.item.readOnly }
      props.onUpdate(newTodo)
    }
  }

  const editTodoHandler = (event) => {
    setcurrentTodoTitle(event.target.value)
  }

  return (
    <ListItem>
      <Checkbox checked={props.item.done} onChange={checkboxHandler}></Checkbox>
      <ListItemText>
        <InputBase
        inputProps={{'aria-label': 'naked', readOnly: props.item.readOnly}}
        onClick={offReadOnlyMode}
        onKeyDown={onReadOnlyMode}
        onChange={editTodoHandler}
        type="text"
        id={`${props.item.id}`} // id와 name은 문자열이어야 하는데 숫자로 넘겨줘서 백틱으로 문자열 처리함
        name={`${props.item.id}`}
        value={currentTodoTitle}
        multiline={true}
        fullWidth={true}
        >
        </InputBase>
      </ListItemText>
      <ListItemSecondaryAction>
        <IconButton 
        aria-label="Delete Todo"
        onClick={deleteTodoHandler}>
          <DeleteOutline></DeleteOutline>
        </IconButton>
      </ListItemSecondaryAction>
    </ListItem>

  )
}

export default Todo;