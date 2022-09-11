package com.epam.sasch.command;

import com.epam.sasch.command.impl.*;

public enum CommandEnum {
    NULL( new SameValueRandomFilling()),
    REC( new RecursiveRelationshipCommand()),
    LOOKUP( new LookupValueCommand()),
    UNION( new UnionFilesInFolderCommand()),
    DELETE( new DeleteCommand());

    private Command command;

    CommandEnum( Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}