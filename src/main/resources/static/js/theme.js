// Theme handling: toggle, set (light|dark|auto), menu control and persistence
const THEME_KEY = 'theme';

function getStoredTheme() {
    try {
        return window.localStorage.getItem(THEME_KEY);
    } catch (error) {
        return null;
    }
}

function storeTheme(theme) {
    try {
        window.localStorage.setItem(THEME_KEY, theme);
    } catch (error) {
        // localStorage can be blocked in some privacy modes
    }
}

function clearStoredTheme() {
    try {
        window.localStorage.removeItem(THEME_KEY);
    } catch (error) {
        // localStorage can be blocked in some privacy modes
    }
}

function applyTheme(theme) {
    if (theme === 'dark') {
        document.body.classList.add('dark');
    } else {
        document.body.classList.remove('dark');
    }
}

function applyAuto() {
    const prefersDark = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
    applyTheme(prefersDark ? 'dark' : 'light');
}

function setTheme(theme) {
    if (theme === 'auto') {
        clearStoredTheme();
        applyAuto();
    } else {
        storeTheme(theme);
        applyTheme(theme);
    }
    closeMenu();
}

function toggleTheme() {
    const isDark = document.body.classList.toggle('dark');
    storeTheme(isDark ? 'dark' : 'light');
}

function toggleMenu() {
    const menu = document.getElementById('themeMenu');
    if (menu) menu.classList.toggle('open');
}

function closeMenu() {
    const menu = document.getElementById('themeMenu');
    if (menu) menu.classList.remove('open');
}

// Close dropdown when clicking outside
document.addEventListener('click', function (e) {
    if (!e.target.closest || !e.target.closest('.theme-dropdown')) {
        closeMenu();
    }
});

function initializeTheme() {
    const saved = getStoredTheme();
    if (saved === 'dark' || saved === 'light') {
        applyTheme(saved);
    } else {
        applyAuto();
    }

    // If user has not chosen a theme, respond to system changes
    if (!getStoredTheme() && window.matchMedia) {
        window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', function () {
            if (!getStoredTheme()) applyAuto();
        });
    }
}

if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initializeTheme);
} else {
    initializeTheme();
}

window.setTheme = setTheme;
window.toggleTheme = toggleTheme;
window.toggleMenu = toggleMenu;