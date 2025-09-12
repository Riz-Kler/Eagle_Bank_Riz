import { useForm } from 'react-hook-form';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';

const registerSchema = z.object({
    fullName: z.string().min(1),
    email: z.string().email(),
    password: z.string().min(5),
    confirm: z.string().min(5)
}).refine(d => d.password === d.confirm, { message: "Passwords must match", path: ["confirm"] });

const loginSchema = z.object({
    email: z.string().email(),
    password: z.string().min(5)
});

export default function AuthPage() {
    const rForm = useForm<z.infer<typeof registerSchema>>({ resolver: zodResolver(registerSchema) });
    const lForm = useForm<z.infer<typeof loginSchema>>({ resolver: zodResolver(loginSchema) });

    const onRegister = async (d: z.infer<typeof registerSchema>) => {
        await fetch('/api/v1/users', {
            method: 'POST',
            headers: { 'Content-Type':'application/json' },
            body: JSON.stringify({ fullName: d.fullName, email: d.email, password: d.password })
        });
        // handle success/error + toast, reset, etc.
    };

    const onLogin = async (d: z.infer<typeof loginSchema>) => {
        const res = await fetch('/api/auth/login', {
            method:'POST',
            headers:{ 'Content-Type':'application/json' },
            body: JSON.stringify(d)
        });
        // expect { token: '...' }
        const json = await res.json();
        localStorage.setItem('token', json.token);
    };

    return (
        <div className="grid md:grid-cols-2 gap-8 p-6">
            <section>
                <h2>New Customer</h2>
                <form onSubmit={rForm.handleSubmit(onRegister)}>
                    <input placeholder="Full name" {...rForm.register('fullName')} />
                    <input placeholder="Email" type="email" {...rForm.register('email')} />
                    <input placeholder="Password" type="password" {...rForm.register('password')} />
                    <input placeholder="Confirm" type="password" {...rForm.register('confirm')} />
                    <button type="submit">Create account</button>
                </form>
            </section>
            <section>
                <h2>Existing Customer</h2>
                <form onSubmit={lForm.handleSubmit(onLogin)}>
                    <input placeholder="Email" type="email" {...lForm.register('email')} />
                    <input placeholder="Password" type="password" {...lForm.register('password')} />
                    <button type="submit">Login</button>
                </form>
            </section>
        </div>
    );
}
