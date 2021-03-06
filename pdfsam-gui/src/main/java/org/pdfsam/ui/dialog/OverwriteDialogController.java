/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 09/ott/2014
 * Copyright 2013-2014 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.pdfsam.ui.dialog;

import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import static org.sejda.eventstudio.StaticStudio.eventStudio;

import javax.inject.Inject;
import javax.inject.Named;

import org.pdfsam.i18n.DefaultI18nContext;
import org.pdfsam.module.TaskExecutionRequestEvent;
import org.sejda.eventstudio.annotation.EventListener;
import org.sejda.eventstudio.exception.BroadcastInterruptionException;
import org.sejda.model.exception.TaskOutputVisitException;
import org.sejda.model.output.DirectoryTaskOutput;
import org.sejda.model.output.FileTaskOutput;
import org.sejda.model.output.StreamTaskOutput;
import org.sejda.model.output.TaskOutputDispatcher;
import org.sejda.model.parameter.base.AbstractParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller receiving task execution requests and displaying the dialog if necessary
 * 
 * @author Andrea Vacondio
 *
 */
@Named
public class OverwriteDialogController {
    private static final Logger LOG = LoggerFactory.getLogger(OverwriteDialogController.class);

    private OverwriteConfirmationDialog dialog;

    @Inject
    public OverwriteDialogController(OverwriteConfirmationDialog dialog) {
        this.dialog = dialog;
        eventStudio().addAnnotatedListeners(this);
    }

    @EventListener(priority = Integer.MIN_VALUE)
    public void request(TaskExecutionRequestEvent event) {
        AbstractParameters params = event.getParameters();
        try {
            if (!params.isOverwrite()) {
                event.getParameters().getOutput().accept(new TaskOutputDispatcher() {

                    public void dispatch(StreamTaskOutput output) {
                        // nothing to do
                    }

                    public void dispatch(DirectoryTaskOutput output) {
                        if (isNotEmpty(output.getDestination().listFiles())) {
                            if (!dialog.title(DefaultI18nContext.getInstance().i18n("Directory not empty"))
                                    .messageTitle(DefaultI18nContext.getInstance()
                                            .i18n("The selected directory is not empty"))
                                    .messageContent(DefaultI18nContext.getInstance()
                                            .i18n("Overwrite files with the same name as the generated ones?"))
                                    .shouldOverwrite()) {
                                throw new BroadcastInterruptionException(
                                        DefaultI18nContext.getInstance().i18n("Don't overwrite existing file"));
                            }
                            LOG.trace("Enabling overwrite of the existing output file");
                            params.setOverwrite(true);
                        }
                    }

                    public void dispatch(FileTaskOutput output) {
                        if (output.getDestination().exists()) {
                            if (!dialog.title(DefaultI18nContext.getInstance().i18n("Overwrite confirmation"))
                                    .messageTitle(DefaultI18nContext.getInstance()
                                            .i18n("A file with the given name already exists"))
                                    .messageContent(
                                            DefaultI18nContext.getInstance().i18n("Do you want to overwrite it?"))
                                    .shouldOverwrite()) {
                                throw new BroadcastInterruptionException(
                                        DefaultI18nContext.getInstance().i18n("Don't overwrite existing file"));
                            }
                            LOG.trace("Enabling overwrite of the existing output file");
                            params.setOverwrite(true);
                        }
                    }
                });
            }
        } catch (TaskOutputVisitException e) {
            // it should never happen
            LOG.warn("Unable to show overwrite confirmation dialog", e);
        }
    }

}
