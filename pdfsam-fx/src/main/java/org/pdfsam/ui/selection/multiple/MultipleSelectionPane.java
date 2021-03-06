/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 28/nov/2013
 * Copyright 2013 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.ui.selection.multiple;

import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.pdfsam.support.RequireUtils.require;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.pdfsam.module.ModuleOwned;
import org.pdfsam.ui.workspace.RestorableView;
import org.sejda.model.parameter.base.TaskParameters;

import javafx.scene.layout.BorderPane;

/**
 * Panel holding the selection table and its toolbar. It is constructed specifying the columns for the selection table and it participates to the {@link TaskParameters} build
 * process
 * 
 * @author Andrea Vacondio
 */
public class MultipleSelectionPane extends BorderPane implements ModuleOwned, RestorableView {

    private String ownerModule = StringUtils.EMPTY;
    private SelectionTable table;

    public MultipleSelectionPane(String ownerModule, SelectionTableColumn<?>... columns) {
        this(ownerModule, false, columns);
    }

    public MultipleSelectionPane(String ownerModule, boolean canDuplicate, SelectionTableColumn<?>... columns) {
        require(columns.length > 0, "No column has been selected");
        this.ownerModule = defaultString(ownerModule);
        setTop(new SelectionTableToolbar(ownerModule));
        table = new SelectionTable(ownerModule, canDuplicate, columns);
        setCenter(table);
    }

    public String getOwnerModule() {
        return ownerModule;
    }

    public SelectionTable table() {
        return table;
    }

    public void saveStateTo(Map<String, String> data) {
        table.saveStateTo(data);
    }

    public void restoreStateFrom(Map<String, String> data) {
        table.restoreStateFrom(data);
    }

}
