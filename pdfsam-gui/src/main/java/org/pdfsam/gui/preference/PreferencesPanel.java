/*
 * Created on 05/apr/2012
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; 
 * either version 2 of the License.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program; 
 * if not, write to the Free Software Foundation, Inc., 
 *  59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package org.pdfsam.gui.preference;

import javax.swing.Icon;

import org.pdfsam.context.DefaultI18nContext;
import org.pdfsam.gui.AbstractContentPanel;

/**
 * Panel showing preferences and allowing the user to set them.
 * 
 * @author Andrea Vacondio
 * 
 */
public class PreferencesPanel extends AbstractContentPanel {

    public PreferencesPanel() {
        add(new LocalesComboBox());
        add(new ThemesComboBox());
    }

    @Override
    public String getPanelId() {
        return "Preferences";
    }

    @Override
    public String getPanelName() {
        return DefaultI18nContext.getInstance().getI18n().tr("Preferences");
    }

    @Override
    public Icon getPanelIcon() {
        // TODO Auto-generated method stub
        return null;
    }

}
